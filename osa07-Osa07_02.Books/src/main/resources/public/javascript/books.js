var url = contextRoot + "books/random"

// Implement the functionality to retrieve a random book here

function getRandomBook() {
    fetch(url)
        .then(response => {
                if (response.ok) {
                    return response.json();
                }
            })
        .then(jsonData => {
                    document.getElementById("title").innerHTML = jsonData.title;
                    document.getElementById("author").innerHTML = jsonData.author;
                    document.getElementById("pages").innerHTML = jsonData.pages;
                }
            ).catch(error => console.log(error.message));
}
