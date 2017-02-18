

function clearText() {
  document.getElementById('searchBox').value ="";
};

function putSearchBack() {
  if (document.getElementById('searchBox').value==="") {
    document.getElementById('searchBox').value = "Search by title...";
  }
};

function updateShows() {
  var shows = ["Lost", "How_I_Met_Your_Mother", "Heroes", "Prison_Break", "The_100", "Fringe", "Scrubs", "The_Walking_Dead"];
  var searchTerm = document.getElementById('searchBox').value.trim().replace(/ /g, '_');
  var children = document.getElementById('shows');
  while (children.hasChildNodes()) {
    children.removeChild(children.firstChild);
  }
  var numShows = shows.length;
  for (var i=0; i<numShows; i++) {
    var currentShow = shows[i];
    if (currentShow.toLowerCase().substring(0,searchTerm.length) === searchTerm.toLowerCase()) {
      var a = document.createElement("a");
      a.setAttribute("class", "showImage");
      a.setAttribute("id", currentShow);
      children.appendChild(a);
    }
  }
};
