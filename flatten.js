var arrays = [[1,2,3], [4,5], [6]];

// Their approach, not happy with
function flatten(arrays) {
  return arrays.reduce(function(acc, curr) {
    acc.concat(curr);
  }, []);
}


// my approach, a bit hacky
function flatten(arrays) {
  return arrays.reduce(function(acc, curr) {
    if (typeof curr == "object")
      curr = flatten(curr);
    return acc.concat(curr);
  }, []);
}

// Nice, thisy I wanty.
function flatten(arr) {
  return arr.reduce(function (flat, toFlatten) {
    return flat.concat(Array.isArray(toFlatten) ? flatten(toFlatten) : toFlatten);
  }, []);
}

console.log(flatten(arrays));
