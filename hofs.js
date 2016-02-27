function filter(arr, test) {
  var out = [];
  for (var i = 0; i < arr.length; i++) {
    if (test(arr[i]))
      out.push(arr[i]);
  }
  return out;
}

function map(arr, fn) {
  var out = [];
  for (var i = 0; i < arr.length; i++) {
    out.push(fn(arr[i]));
  }
  return out;
}

function reduce(arr, compose, start) {
  var current = start;
  for (var i = 0; i < arr.length; i++) {
    current = compose(current, arr[i]);
  }
  return current;
}

filter([1,2,3,4], function(x) { return x%2 == 0; });
map([1,2,3,4], function(x) { return x + 1; });
reduce([1,2,3,4], function(a,b) { return a + b; }, 0);
