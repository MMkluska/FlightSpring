"use strict";

// Selectors

let createBtn = document.querySelector("#createBtn");
let deleteBtn = document.querySelector("#deleteBtn");
let updateBtn = document.querySelector("#updateBtn");

let startInput = document.querySelector("#startInput");
let endInput = document.querySelector("#endInput");
let airlinesInput = document.querySelector("#airlinesInput");
let dateInput = document.querySelector("#dateInput");
let priceInput = document.querySelector("#priceInput");
let idUpdate = document.querySelector("#idUpdate");
let idDelete = document.querySelector("#idDelete");

let resultDiv = document.querySelector("#resultDiv");

// Functions

let print = (result) => {
    let tempDiv = document.createElement("div");
    tempDiv.setAttribute("class", "tempDiv");
    tempDiv.textContent = `Id: ${result.id} | Departure: ${result.startLocation} | Arrival: ${result.endLocation} | Airlines: ${result.airlines} | Date: ${result.date} | Price: ${result.price}`;
    resultDiv.appendChild(tempDiv);

}

let getAll = () => {
    axios.get("http://localhost:8080/flight/getAll")
        .then(res => {

            resultDiv.innerHTML = "";

            let results = res.data;

            for (let result of results) {
                print(result);
            }
        })
        .catch(err => {
            console.log(err);
        });

}

let create = () => {

    let obj = {
        "startLocation": startInput.value,
        "endLocation": endInput.value,
        "airlines": airlinesInput.value,
        "date": dateInput.value,
        "price": priceInput.value
    }

    axios.post("http://localhost:8080/flight/create", obj)
        .then(res => {
            getAll();
        })
        .catch(err => {
            console.log(err);
        });
}

let update = () => {

    let obj = {
        "id": idUpdate.value,
        "startLocation": startInput.value,
        "endLocation": endInput.value,
        "airlines": airlinesInput.value,
        "date": dateInput.value,
        "price": priceInput.value
    }

    axios.put(`http://localhost:8080/flight/update/${idUpdate.value}`, obj)
        .then(res => {
            getAll();
        })
        .catch(err => {
            console.log(err);
        });
}

let delById = () => {
    axios.delete(`http://localhost:8080/flight/delete/${idDelete.value}`)
        .then(res => {
            getAll();
        })
        .catch(err => {
            console.log(err);
        });
}

// Listners

createBtn.addEventListener("click", create);
deleteBtn.addEventListener("click", delById);
updateBtn.addEventListener("click", update);