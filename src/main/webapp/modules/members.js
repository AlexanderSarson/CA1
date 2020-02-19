import { printTableArray } from "./html.js";
import { getDataFromAPIAppendToContainer } from "./api.js";

const markup = `
  <div>
    <h1 align="center">Gruppe 2</h1>
    <h2 align="center">Members (loaded from a REST API)</h2>
    <button id="reloadBtn">Reload Names</button>
  </div>
`;

const getAllMembersUrl = "https://sarson.codes/CA1/api/groupmembers/all";

const showAllMembers = container => {
  getDataFromAPIAppendToContainer(getAllMembersUrl, container, printTableArray);
};

export const showMembers = (parentContainer, container) => {
  parentContainer.innerHTML = markup;
  parentContainer.appendChild(container);
  showAllMembers(container);
  const button = document.querySelector("#reloadBtn");
  button.addEventListener("click", () => {
    showAllMembers(container);
  });
};
