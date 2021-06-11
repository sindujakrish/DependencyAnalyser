package com.zealousdev;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.zealousdev.CircularDependencyException;
import com.zealousdev.DependencyAnalyser;

public class DependencyAnalyserTest {
	
	@Test
	public void testPositiveCase() throws CircularDependencyException {
		DependencyAnalyser app = new DependencyAnalyser();
		String[] inputString = { "A B C", "B C E", "C G", "D A F", "E F", "F H" };
		app.readInput(inputString);
		app.printAlldependencies();
		assertThat(app.getDependenciesFor("A"), equalTo("B C E F G H"));
		assertThat(app.getDependenciesFor("B"), equalTo("C E F G H"));
		assertThat(app.getDependenciesFor("C"), equalTo("G"));
		assertThat(app.getDependenciesFor("D"), equalTo("A B C E F G H"));
		assertThat(app.getDependenciesFor("E"), equalTo("F H"));
		assertThat(app.getDependenciesFor("F"), equalTo("H"));
		assertThat(app.getDependenciesFor("G"), equalTo(""));
		assertThat(app.getDependenciesFor("H"), equalTo(""));
	}
	
	@Test(expected = CircularDependencyException.class)
	public void testNegativeCase() throws CircularDependencyException {
		DependencyAnalyser app = new DependencyAnalyser();
		String[] inputString = { "A B", "B A"};
		app.readInput(inputString);
	}
}
