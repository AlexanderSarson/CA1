import { showMembers } from "./modules/members";
import { showJokes } from "./modules/jokes";
import { showCars } from "./modules/cars";

const rootContainer = document.querySelector("#root");
const parentContainer = document.createElement("div");
const container = document.createElement("div");
const membersBtn = document.querySelector("#showMembers");
const jokesBtn = document.querySelector("#showJokes");
const carsBtn = document.querySelector("#showCars");
rootContainer.append(parentContainer);

const renderMembers = () => {
  showMembers(parentContainer, container);
};

const renderJokes = () => {
  showJokes(parentContainer, container);
};

const renderCars = () => {
  showCars(parentContainer, container);
};

membersBtn.addEventListener("click", renderMembers);
jokesBtn.addEventListener("click", renderJokes);
carsBtn.addEventListener("click", renderCars);
