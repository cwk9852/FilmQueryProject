package com.skilldistillery.filmquery.app;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class TestFilmQueryApp {
	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws Exception {
		TestFilmQueryApp app = new TestFilmQueryApp();
		app.test();

	}

	public void test() throws Exception {
		Film film = db.findFilmById(1);
		System.out.println(film.toStringDetails());
		Actor actor = db.findActorById(22);
		System.out.println(actor);
//		List<Actor> actorsByFilmId = db.findActorsByFilmId(1);
//		List<Film> filmsByActorId = db.findFilmsByActorId(1);
//		List<Film> filmsByKeyword = db.findFilmsByKeyword("academy");
	}
}
