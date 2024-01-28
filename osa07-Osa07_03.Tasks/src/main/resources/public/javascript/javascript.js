var url = contextRoot + "tasks";
getTasks();

function getTasks() {
    fetch(url)
    .then(response => response.ok ? response.json() : console.log(response.status))
    .then(jsonData => {
        var taskUl = document.getElementById("tasks");
        taskUl.innerHTML = "";
        for (var i = 0; i < jsonData.length; i++) {
            var liElement = document.createElement('li');
            liElement.innerText = jsonData[i].name;

            taskUl.appendChild(liElement);
        }
    }).catch(error => console.log(error));
}


function postTask() {
    var nameTask = document.getElementById("name").value;
    fetch(url, {
        method: "POST",
        body: JSON.stringify({name:  nameTask}),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
    .then(response => response.ok ? response.json() : console.log(response.status))
    .then(jsonData => getTasks())
    .catch(error => console.log(error));
}