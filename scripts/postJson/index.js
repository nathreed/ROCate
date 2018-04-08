var http = require("http");
var fs = require("fs");

var fileData = fs.readFileSync("./jsonToSend.json");

var options = {
	hostname: "localhost",
	port: 8888,
	path: "/rocate/position",
	method: 'POST',
	headers: {
		'Content-Type': "application/json"
	}
};

var req = http.request(options, function(res) {
	res.on("data", function(body) {
		console.log('resp body:');
		console.log(body.toString());
	})
});

req.write(fileData);
req.end();