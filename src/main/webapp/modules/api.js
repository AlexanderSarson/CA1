export const getDataFromAPIAppendToContainer = (
  url,
  div,
  callback,
  ...args
) => {
  fetch(url)
    .then(response => {
      return response.json();
    })
    .then(json => {
      div.innerHTML = callback(json, args);
    });
};
