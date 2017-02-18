import imdb

ia = imdb.IMDb()

series = ["Lost","The 100","Dexter","24","Smallville"]

for s in series:
    results = ia.search_movie(s)
    for result in results:
        if result["kind"] == 'tv series':
            print result
            print result.movieID
            break
