function arrayToList(array) {
  var list = null;
  for (var i = array.length - 1; i >= 0; i--)
    list = {value: array[i], rest: list};
  return list;
}

function listToArray(list) {
  var array = [];
  for (var node = list; node; node = node.rest)
    array.push(node.value)
  return array;
}

function prepend(value, list) {
  return {value: value, rest: list};
}

function nth(list, n) {
  if (n < 0 || !list)
    return undefined;
  else if (n == 0)
    return list.value;
  else
    return nth(list.rest, n-1);
}

var foo = arrayToList([0, 1, 2]);

console.log(listToArray(foo));
