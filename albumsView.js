export default function albumsView(album){
    return `
        <main class="main-content">
        <div class="album-header">
                    <h2 class="campus-library-header__title">${album.title}</h2>
                    <h3 class="campus-library-header__recordLabel">${album.recordLabel}</h3>
                    </div>
                <div class="albums-container">
                    ${
                        album.songs.map(song => {
                            return `
                                <div class="song">
                                <iframe style="border-radius:12px"
        src="${song.src}" width="380"
        height="380" frameBorder="0" allowfullscreen=""
        allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture"></iframe>
                                <input type="hidden" class="id_field" value="${song.id}">
                                    <h3 class="song-title">${song.name}</h3>
                                    <button class="deleteSongButton">delete song</button>
                                    <input type="text" placeholder="New Song Name" class="newNameInput" \>
                                    <button class="changeNameButton">Change Song Name</button>
                                </div>
                            `
                        }).join("")
                    }
                </div>
                <div class="NewSongDiv">
                    <input type="text" placeholder="Song Name" class="songNameInput" \>
                    <input type="number" placeholder="Song Duration" class="songDurationInput" \>
                    <input type="text" placeholder="src" class="songSrcInput" \>
                    
                    <button class="addsongButton">Add song</button>
                </div>
                <a class="back-navigation">back to album listings</a>
        </main>
    `
}