# UnBind Server
Web server for use with the UnBind Application.
When deployed, syntax for getting the route is:
```
http://localhost:8080/UnBindServer/Route?lat1=???&lon1=???&lat2=???&lon2=???
```
where you set the ???? with your coordinates.

## Setting up Database
The database runs in MySQL. Run the unbind_db.sql to setup the database.

## OSM parser
The parser is used for parsing .osm files for an area and storing in a database. Nodes have yet to be filtered completely. **Make sure to copy the contents of the edges_start to edges_node as well for the server to work.**

## Additional setup
Make sure to duplicate the contents of the edges table into the other table manually as inserting programatically would further increase the parsing time.
