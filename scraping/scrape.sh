shows=("The 100" "Dexter" "24" "Smallville" "Veronica Mars" "Prison Break" "Heroes" "Homeland" "Nip/Tuck" "True Blood")
for show in $shows
do
  echo $show
  python scrape_imdb.py $show
  sleep 2m
done