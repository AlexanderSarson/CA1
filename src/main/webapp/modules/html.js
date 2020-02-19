export const printTableArray = array => {
  let table = "<table align='center'>";
  table += "<tr>";
  for (let key of Object.keys(array[0])) {
    table += `<th id=${key}> ${key} </th>`;
  }
  table += "</tr>";
  array.forEach(member => {
    table += "<tr>";
    for (let values of Object.values(member)) {
      table += `<td> ${values} </td>`;
    }
    table += "</tr>";
  });
  table += "</table>";
  return table;
};

export const printTable = obj => {
  let table = "<table align='center'>";
  table += "<tr>";
  for (let key of Object.keys(obj)) {
    table += `<th> ${key} </th>`;
  }
  table += "</tr>";
  for (let values of Object.values(obj)) {
    table += `<td> ${values} </td>`;
  }
  table += "</tr>";
  table += "</table>";
  return table;
};

/*
export const printTableArray = array => {
  let table = "<table align='center'>";
  table += "<tr>";
  for (let key of Object.keys(array[0])) {
    table += `<th> ${key} </th>`;
  }
  table += "</tr>";
  array.forEach(member => {
    table += "<tr>";
    for (let values of Object.values(member)) {
      table += `<td> ${values} </td>`;
    }
    table += "</tr>";
  });
  table += "</table>";
  return table;
};

export const printTableArray = array => {
  const table = `
  <table align='center'>
    <tr>
    ${Object.keys(array[0])
      .map(
        key => `
      <th>${key}</th>
    `
      )
      .join("")}
    </tr>
    ${array
      .map(
        value => `
      <tr>
      ${Object.values(value).map(
        propValue => `
      <td> ${propValue} </td>
      `
      )}
      </tr>`
      )
      .join("")}
  </table>`;
  return table;
};

*/
