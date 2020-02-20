import { printTableArray } from "./html.js";

const state = {
  cars: []
};

const markup = `
  <div>
    <h1 align="center"> Cars!</h1>
    <button id="showAllCarsBtn">Show all cars</button>
    <div>
      <p>Filter by year</p>
      <input id="yearInput"></input>
      <p>Filter by make</p>
      <input id="makeInput"></input>
      <p>Filter by model</p>
      <input id="modelInput"></input>
      <p>Filter by price</p>
      <input id="priceInput"></input>
      <div>
        <br>
        <button id="filterBtn">Apply filter</button>
        <button id="clearFilterBtn">Clear filter</button>
      </div>
    </div>
  </div>
`;

const getAllCarsUrl = "https://sarson.codes/CA1/api/cars/all";

const sortCars = (sort, container, carArr) => {
  let carsSorted = carArr;
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
  addSortListeners(container, carsSorted);
};

const addSortListeners = (container, carArr) => {
  const carId = document.querySelector("#id");
  const carYear = document.querySelector("#year");
  const carMake = document.querySelector("#make");
  const carModel = document.querySelector("#model");
  const carPrice = document.querySelector("#price");

  carId.addEventListener("click", () => {
    sortCars(carId.id, container, carArr);
  });

  carYear.addEventListener("click", () => {
    sortCars(carYear.id, container, carArr);
  });
  carMake.addEventListener("click", () => {
    sortCars(carMake.id, container, carArr);
  });
  carModel.addEventListener("click", () => {
    sortCars(carModel.id, container, carArr);
  });
  carPrice.addEventListener("click", () => {
    sortCars(carPrice.id, container, carArr);
  });
};

const filterPrice = (carPrice, filterPrice) => {
  return filterPrice === "" || carPrice <= Number(filterPrice) ? true : false;
};

const filterYear = (carYear, filterYear) => {
  return filterYear === "" || carYear <= Number(filterYear) ? true : false;
};

const filterMake = (carMake, filterMake) => {
  return filterMake === "" ||
    carMake.toLowerCase().includes(filterMake.toLowerCase())
    ? true
    : false;
};

const filterModel = (carModel, filterModel) => {
  return filterModel === "" ||
    carModel.toLowerCase().includes(filterModel.toLowerCase())
    ? true
    : false;
};

const filterHandler = (carArr, container) => {
  const yearInput = document.querySelector("#yearInput").value;
  const makeInput = document.querySelector("#makeInput").value;
  const modelInput = document.querySelector("#modelInput").value;
  const priceInput = document.querySelector("#priceInput").value;
  let html;
  let carFilter = carArr.filter(({ year, make, model, price }) => {
    return (
      filterPrice(price, priceInput) &&
      filterYear(year, yearInput) &&
      filterMake(make, makeInput) &&
      filterModel(model, modelInput)
    );
  });

  if (carFilter.length > 0) {
    html = printTableArray(carFilter);
  } else {
    html = `<h1 align="center"> No cars available</h1>`;
  }
  container.innerHTML = html;
  return carFilter;
};

const addListeners = container => {
  const showAllCarsBtn = document.querySelector("#showAllCarsBtn");
  const filterBtn = document.querySelector("#filterBtn");
  const clearFilterBtn = document.querySelector("#clearFilterBtn");

  clearFilterBtn.addEventListener("click", () => {
    showAllCars(container);
  });
  showAllCarsBtn.addEventListener("click", () => {
    showAllCars(container);
  });
  filterBtn.addEventListener("click", () => {
    filter(container);
  });
};

const getDataFromAPIAppendToContainer = (url, div, callback, ...args) => {
  fetch(url)
    .then(response => {
      return response.json();
    })
    .then(json => {
      div.innerHTML = callback(json, args);
      state.cars = json;
      addSortListeners(div, json);
    });
};

const showAllCars = container => {
  getDataFromAPIAppendToContainer(getAllCarsUrl, container, printTableArray);
};

const filter = container => {
  addSortListeners(container, filterHandler(state.cars, container));
};

export const showCars = (parentContainer, container) => {
  container.innerHTML = "";
  parentContainer.innerHTML = markup;
  parentContainer.appendChild(container);
  addListeners(container);
  showAllCars(container);
};
