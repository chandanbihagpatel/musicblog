import home from "./home.js"
import header from "./header.js"
import footer from "./footer.js"
import albumsView from "./albumsView.js";

const container = document.querySelector(".container");
function makeHomeView(){
    fetch(`http://localhost:8080/api/albums`)
    .then(res => res.json())
    .then(albums => {
        makeHomeViewFromJSON(albums);
    })
}

function makeHomeViewFromJSON(albums){
    console.log(albums);
    container.innerHTML = header();
    container.innerHTML += home(albums);
    container.innerHTML += footer();

    const albumEl = container.querySelectorAll(".album");

    albumEl.forEach(album => {
        let albumElId = album.querySelector(".id_field");
        const albumCover = album.querySelector(".album-cover");
        albumCover.addEventListener("click", () =>{
            // albums.forEach(albumJson => {
            //     makeAlbumView(albumJson)
            // })
            fetch(`http://localhost:8080/api/albums/${albumElId.value}`)
            .then(res => res.json())
            .then(album =>{
                makeAlbumView(album);
            })
        })
    const deleteButton = album.querySelector(".delete-button");
    deleteButton.addEventListener("click", () => {
        fetch(`http://localhost:8080/api/albums/${albumElId.value}`, {
            method: 'DELETE'
        })
        .then(res => res.json())
        .then(newAlbums => {
            makeHomeViewFromJSON(newAlbums);
        })
    })
    const updateButton = album.querySelector(".update-button");
    updateButton.addEventListener("click", () => {
        const updateInput = album.querySelector(".update-title");
        if(updateInput.value){
        fetch(`http://localhost:8080/api/albums/${albumElId.value}/title`,{
            method: 'PATCH',
            body: updateInput.value
        })
       .then(res => res.json())
       .then(newAlbums => {
            makeHomeViewFromJSON(newAlbums);
       })
    }
    else{
        console.error("no input given")
    }
    })
})
}
function makeAlbumView(album) {
        console.log(album);
        document.body.style.backgroundImage = `linear-gradient(black, black), url(${album.imgUrl})`;
        document.body.style.backgroundSize = "cover";
        document.body.style.backgroundBlendMode = "saturation";
        container.innerHTML = header();
        container.innerHTML += albumsView(album);
        container.innerHTML += footer();

        const songsElement = document.querySelectorAll(".song");


        const backButton = document.querySelector(".back-navigation");
        backButton.addEventListener("click",()=>{
            document.body.style.backgroundImage = `none`;
            makeHomeView();
        })

        const songNameIn = container.querySelector(".songNameInput");
        const songSumIn = container.querySelector(".songDurationInput");
        const songSrcIn = container.querySelector(".songSrcInput");
        const addSongButton = container.querySelector(".addsongButton");

        addSongButton.addEventListener("click",()=>{
            const newSongJson = {
                "name": songNameIn.value,
                "duration": songSumIn.value,
                "src": songSrcIn.value,
            }
            fetch(`http://localhost:8080/api/albums/${album.id}/addSong`,{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newSongJson)
            })
            .then(res => res.json())
            .then(album => {
                makeAlbumView(album);
            })
        })

        songsElement.forEach(song =>{
            const deleteButton = song.querySelector(".deleteSongButton");
            const songElId = song.querySelector(".id_field");
            const newNameButton = song.querySelector(".changeNameButton")
            const newNameEl = song.querySelector(".newNameInput")
            deleteButton.addEventListener("click",()=>{
                fetch(`http://localhost:8080/api/songs/${songElId.value}`,{
                    method: 'DELETE'
                })
                .then(res => res.json())
                .then(album => {
                    console.log(album)
                    makeAlbumView(album);
                })
            })
            newNameButton.addEventListener("click", () => {
                if(newNameEl.value){
                fetch(`http://localhost:8080/api/songs/${songElId.value}/name`,{
                    method: 'PATCH',
                    body: newNameEl.value
                })
               .then(res => res.json())
               .then(newAlbum => {
                    makeAlbumView(newAlbum);
               })
            }
            })
        })
}

makeHomeView()