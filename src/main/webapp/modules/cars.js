import { printTableArray } from "./html.js";
var cars;

const markup = `
  <div>
    <button id="showAllCarsBtn">Show all cars</button>
    <div>
      <button id="priceLowerThanBtn">Price lower than</button>
      <input id="priceLowerThanInput"></input>
    </div>
  </div>
`;

const getAllCarsUrl = "https://sarson.codes/CA1/api/cars/all";

const sortCars = (sort, container) => {
  let carsSorted = cars;
  switch (sort) {
    case "id":
      carsSorted.sort((a, b) => a.id - b.id);
      break;
    case "year":
      carsSorted.sort((a, b) => a.year - b.year);
      break;
    case "make":
      carsSorted.sort((a, b) =>
        a.make.toLowerCase().localeCompare(b.make.toLowerCase())
      );
      break;
    case "model":
      carsSorted.sort((a, b) =>
        a.model.toLowerCase().localeCompare(b.model.toLowerCase())
      );
      break;
    case "price":
      carsSorted.sort((a, b) => a.price - b.price);
      break;
    default:
      break;
  }
  container.innerHTML = printTableArray(carsSorted);
  addSortListeners(container);
};

const addSortListeners = container => {
  const carId = document.querySelector("#id");
  const carYear = document.querySelector("#year");
  const carMake = document.querySelector("#make");
  const carModel = document.querySelector("#model");
  const carPrice = document.querySelector("#price");

  carId.addEventListener("click", () => {
    sortCars(carId.id, container);
  });

  carYear.addEventListener("click", () => {
    sortCars(carYear.id, container);
  });
  carMake.addEventListener("click", () => {
    sortCars(carMake.id, container);
  });
  carModel.addEventListener("click", () => {
    sortCars(carModel.id, container);
  });
  carPrice.addEventListener("click", () => {
    sortCars(carPrice.id, container);
  });
};

const filterPrice = (array, search) => {
  let html;
  let carFilter = array.filter(({ price }) => {
    return Number(price) < Number(search) ? true : false;
  });
  if (carFilter.length > 0) {
    html = `<h1 align="center"> Cars available under ${search}</h1>
    ${printTableArray(carFilter)}`;
  } else {
    html = `<h1 align="center"> No cars available under ${search}</h1>`;
  }
  return html;
};

const addListeners = container => {
  const showAllCarsBtn = document.querySelector("#showAllCarsBtn");
  const priceLowerThanBtn = document.querySelector("#priceLowerThanBtn");
  const priceLowerThanInput = document.querySelector("#priceLowerThanInput");

  showAllCarsBtn.addEventListener("click", () => {
    showAllCars(container);
  });
  priceLowerThanBtn.addEventListener("click", () => {
    priceLowerThan(container, priceLowerThanInput.value);
  });
};

const getDataFromAPIAppendToContainer = (url, div, callback, ...args) => {
  fetch(url)
    .then(response => {
      return response.json();
    })
    .then(json => {
      div.innerHTML = callback(json, args);
      cars = json;
      addSortListeners(div);
    });
};

const showAllCars = container => {
  getDataFromAPIAppendToContainer(getAllCarsUrl, container, printTableArray);
};
const priceLowerThan = (container, search) => {
  getDataFromAPIAppendToContainer(
    getAllCarsUrl,
    container,
    filterPrice,
    search
  );
};

export const showCars = (parentContainer, container) => {
  container.innerHTML = "";
  parentContainer.innerHTML = markup;
  parentContainer.appendChild(container);
  addListeners(container);
};
