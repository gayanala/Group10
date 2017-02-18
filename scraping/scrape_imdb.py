import imdb
import csv

ia = imdb.IMDb()

series = ["Lost", "The 100", "Dexter", "24", "Smallville", "Veronica Mars", "Prison Break", "Heroes", "Homeland",
          "Nip/Tuck", "True Blood"]
with open("episode_ratings.csv","w") as outfile:
    writer = csv.writer(outfile)
    for s in series:
        results = ia.search_movie(s)
        movie_id = results[0].movieID
        show = ia.get_movie(movie_id)
        ia.update(show, "episodes")
        for season_num, season in show['episodes'].items():
            for episode_num,episode in season.items():
                episode = ia.get_movie(episode.movieID)
                row = [season_num,episode_num]
                for key in ["smart canonical series title", "smart canonical episode title", "rating", "vote", "year"]:
                    try:
                        row.append(episode[key])
                    except KeyError:
                        row.append("")
                writer.writerow(row)



