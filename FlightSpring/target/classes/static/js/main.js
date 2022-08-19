"use strict";

// Selectors

let createBtn = document.querySelector("#createBtn");

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


    let childDiv = document.createElement("div");
    tempDiv.setAttribute("class", "childDiv");
    
    childDiv.textContent = `Id: ${result.id} | Departure: ${result.startLocation} | Arrival: ${result.endLocation} | Airlines: ${result.airlines} | Date: ${result.date} | Price: ${result.price}`;

    let updateBtn = document.createElement("button");
    updateBtn.textContent = "Update";
    updateBtn.type = "button";
    updateBtn.setAttribute("Class", "btn btn-sm btn-primary ");
    updateBtn.setAttribute("onClick", `update(${result.id})`);

    let deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.type = "button";
    deleteBtn.setAttribute("Class", "btn btn-sm btn-danger ");
    deleteBtn.setAttribute("onClick", `delById(${result.id})`);

    childDiv.appendChild(updateBtn);
    childDiv.appendChild(deleteBtn);
    tempDiv.appendChild(childDiv);
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

let update = (id) => {

    let obj = {
        "id": id,
        "startLocation": startInput.value,
        "endLocation": endInput.value,
        "airlines": airlinesInput.value,
        "date": dateInput.value,
        "price": priceInput.value
    }

    axios.put(`http://localhost:8080/flight/update/${id}`, obj)
        .then(res => {
            getAll();
        })
        .catch(err => {
            console.log(err);
        });
}

let delById = (id) => {
    axios.delete(`http://localhost:8080/flight/delete/${id}`)
        .then(res => {
            getAll();
        })
        .catch(err => {
            console.log(err);
        });
}

// Listners

createBtn.addEventListener("click", create);