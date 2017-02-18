import imdb
import csv

ia = imdb.IMDb()

series = ["Lost","The 100","Dexter","24","Smallville","Veronica Mars","Prison Break","Heroes","Homeland","Nip/Tuck","True Blood"]
with open("episode_ratings.csv","w") as outfile:
    writer = csv.writer(outfile)
    for s in series:
        results = ia.search_movie(s)
        movie_id = results[0].movieID
        show = ia.get_movie(movie_id)
        ia.update(show, "episodes")
        episode_id = show['episodes']
        for season in show['episodes']:
            for episode in season:
                episode = ia.get_movie(episode.movieID)

                writer.writerow([episode["rating"])



