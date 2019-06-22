package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private final String USERNAME = "student";
	private final String PASSWORD = "student";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "SELECT * FROM film WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, filmId);
		ResultSet rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				String title = rs.getString(2);
				String desc = rs.getString(3);
				String releaseYear = rs.getString(4);
				String langId = rs.getString(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
			}
		} catch (SQLException e) {
			processException(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "SELECT first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, actorId);
		ResultSet rs = pstmt.executeQuery();
		try {
			if (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				actor.setFilms(findFilmsByActorId(actorId));
			}
		} catch (SQLException e) {
			processException(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) throws SQLException {
		List<Film> films = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, actorId);
		ResultSet rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				int filmId = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				String releaseYear = rs.getString(4);
				String langId = rs.getString(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
		} catch (SQLException e) {
			processException(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "select actor.first_name, actor.last_name, film.title from actor join film_actor on actor.id = film_actor.actor_id join film on film.id = film_actor.film_id where film.id =?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, filmId);
		ResultSet rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				String firstName = rs.getString(1);
				String lastName = rs.getString(2);
				Actor actor = new Actor(filmId, firstName, lastName);
				actors.add(actor);
			}
		} catch (SQLException e) {
			processException(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return actors;
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) throws SQLException {
		List<Film> films = new ArrayList<>();
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features FROM film WHERE film.title LIKE ? OR film.description LIKE ?;";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				int filmId = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				String releaseYear = rs.getString(4);
				String langId = rs.getString(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(11);
				Film film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
		} catch (SQLException e) {
			processException(e);
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		return films;
	}

	public static void processException(SQLException e) {
		System.err.println("Error message: " + e.getMessage());
		System.err.println("Error code: " + e.getErrorCode());
		System.err.println("SQL state: " + e.getSQLState());
	}
}
