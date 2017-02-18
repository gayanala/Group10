import imdb
import csv
import argparse
import time

parser = argparse.ArgumentParser()
parser.add_argument("show_title")
args=parser.parse_args()

ia = imdb.IMDb()

series = ["Lost", "The 100", "Dexter", "24", "Smallville", "Veronica Mars", "Prison Break", "Heroes", "Homeland",
          "Nip/Tuck", "True Blood"]
with open(args.show_title+".csv","w") as outfile:
    writer = csv.writer(outfile)

    results = ia.search_movie(args.show_title)
    movie_id = results[0].movieID
    show = ia.get_movie(movie_id)
    ia.update(show, "episodes")
    for season_num, season in show['episodes'].items():
        for episode_num,episode in season.items():
            episode = ia.get_movie(episode.movieID)
            row = [season_num,episode_num]
            for key in ["smart canonical series title", "smart canonical episode title", "rating", "votes", "year"]:
                try:
                    row.append(episode[key])
                except KeyError:
                    row.append("")
            try:
                writer.writerow(row)
            except UnicodeEncodeError:
                print "unicode error"
            time.sleep(5)



