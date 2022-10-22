# Movies - Compose

This is a compose test project to practice compose-UI and DI whit Dagger-Hilt
First and going to create a traditional project with xml views and then and going to change to compose-UI.


1. TV Shows list
- create a scroll screen with movies.
- Endpoint: the Most Popular Tv Shows.
  (https://developers.themoviedb.org/3/tv/get-popular-tv-shows)
- The item has an image, title y and review average. 
- The List must be able to hand pagination.
2. Detail Screen
- Separate view (other screen).
- it should be have an image, title, description
  (you can retrieve further data from the previous endpoint mentioned).
- The user could be able to navigate to suggested shows. There is located at the bottom on the screen in a recyclerview.
- Similar shows endpoint:
  (https://developers.themoviedb.org/3/tv/get-similar-tv-shows)
  
You must write a api_key_movie on gradel.properties to run this project. 


![untitled](https://user-images.githubusercontent.com/9206032/197360584-d9e057b0-ae41-4a46-96ef-9e54eacade62.gif)
