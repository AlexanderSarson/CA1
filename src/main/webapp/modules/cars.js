import { getDataFromAPIAppendToContainer } from "./api";
import { printTableArray } from "./html";
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
const showAllCars = container => {
  getDataFromAPIAppendToContainer(getAllCarsUrl, container, printTableArray);
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

const priceLowerThan = (container, search) => {
  getDataFromAPIAppendToContainer(
    getAllCarsUrl,
    container,
    filterPrice,
    search
  );
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

export const showCars = (parentContainer, container) => {
  container.innerHTML = "";
  parentContainer.innerHTML = markup;
  parentContainer.appendChild(container);
  addListeners(container);
};
