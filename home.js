export default function home(albums){
    return `
    <main class="albums-container">
    ${albums.map(album =>{
        return `
        <div class ="album">
        <img class="album-cover" src="${album.imgUrl}">
        <input type="hidden" class="id_field" value="${album.id}">
        <h2 class="album-title">${album.title}</h2>
        <h3 class="album-recordLabel">${album.recordLabel}</h3>

        <input type="text" class="update-title" placeholder="New Album Title">
        <button class="update-button">Update Title</button>
        <button class="delete-button">Delete Album</button>
        </div>
        `
    }).join("")
}
    </main>
    `
}