package com.skilldistillery.filmquery.app;

import java.util.List;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;
import com.skilldistillery.filmquery.util.InputHelper;

public class FilmQueryApp {
	InputHelper ih = new InputHelper();
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws Exception {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();
		app.launch();
	}

	private void launch() throws Exception {

		startUserInterface();

	}

	private void startUserInterface() throws Exception {
		switch (InputHelper.getIntegerInput("1. Look Up Film By ID\n2. Search by Keyword\n3. Exit\n:")) {
		case 1:
			try {
				Film film = db.findFilmById(InputHelper.getIntegerInput("Enter Film ID:"));
				if (film != null) {
				System.out.println(film.toString());
				} else {
					System.out.println();
				}
			} catch (NumberFormatException e) {
				System.err.println("Error: invalid number");
				return;
			}
			break;
		case 2:
			try {
				List<Film> films = db.findFilmsByKeyword(InputHelper.getInput("Enter Keyword:"));
				if (films.size() > 0) {
					for (Film film : films) {
						System.out.println(film.toString());
					}
				} else {
					System.out.println("No Films Found");
				}
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				return;
			}
			break;
		case 3:
			break;
		default:
			break;
		}
	}
}
//	private void test() throws Exception {
//	    Film film = db.findFilmById(1);
//	    Actor actor = db.findActorById(1);
//	    List<Actor> actors = db.findActorsByFilmId(1);
//	    List<Film> films = db.findFilmsByActorId(1);
//	    List<Film> films = db.searchFilmByKeyword("academy");
//	}