import { printTableArray, printTable } from "./html";
import { getDataFromAPIAppendToContainer } from "./api";

const markup = `
  <div>
    <button id="showAllJokesBtn">Show All Jokes</button>
    <div>
      <button id="getJokeByIdBtn">Get joke by id</button>
      <input id="getJokeByIdInput"></input>
      <div>
        <button id="getRandomJokeBtn">Get random joke</button>
      </div>
    </div>
  </div>
`;

const getAllJokesUrl = "https://sarson.codes/CA1/api/jokes/all";
const getJokeByIdUrl = "https://sarson.codes/CA1/api/jokes/id/";
const getRandomJokeUrl = "https://sarson.codes/CA1/api/jokes/random";

const showAllJokes = container => {
  getDataFromAPIAppendToContainer(getAllJokesUrl, container, printTableArray);
};

const getJokeById = (container, id) => {
  const getJokeById = getJokeByIdUrl + id;
  getDataFromAPIAppendToContainer(getJokeById, container, printTable);
};

const getRandomJoke = container => {
  getDataFromAPIAppendToContainer(getRandomJokeUrl, container, printTable);
};

const addListeners = container => {
  const showAllJokesBtn = document.querySelector("#showAllJokesBtn");
  const getJokeByIdBtn = document.querySelector("#getJokeByIdBtn");
  const getRandomJokeBtn = document.querySelector("#getRandomJokeBtn");
  const getJokeByIdInput = document.querySelector("#getJokeByIdInput");

  showAllJokesBtn.addEventListener("click", () => {
    showAllJokes(container);
  });
  getJokeByIdBtn.addEventListener("click", () => {
    getJokeById(container, getJokeByIdInput.value);
    getJokeByIdInput.value = "";
  });
  getRandomJokeBtn.addEventListener("click", () => {
    getRandomJoke(container);
  });
};

export const showJokes = (parentContainer, container) => {
  parentContainer.innerHTML = markup;
  container.innerHTML = "";
  parentContainer.appendChild(container);
  addListeners(container);
};
