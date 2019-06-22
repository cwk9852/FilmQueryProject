package com.skilldistillery.filmquery.app;

import java.util.List;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

import resources.InputHelper;

public class FilmQueryApp {
	InputHelper ih = new InputHelper();
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws Exception {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() throws Exception {

		startUserInterface();

	}

	private void startUserInterface() throws Exception {
		switch (InputHelper.getIntegerInput("1. Find Film By ID\n2. Find Films By Keyword\n3. Exit\n:")) {
		case 1:
			try {
				Film film = db.findFilmById(InputHelper.getIntegerInput("Enter Film ID:"));
				if (film != null) {
					System.out.println(film.toStringDetails());
					List<Actor> cast = db.findActorsByFilmId(film.getFilmId());
					System.out.println("Lead Cast:");
					for (Actor actor : cast) {
						System.out.println(actor.toString());
					}
				} else {
					System.out.println("No Film Found");
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
					System.out.println("Found:");
					for (Film film : films) {
						System.out.println(film.toStringDetails());
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