Trying to get debux to work with shadow, not sure what's going on.

Steps to reproduce

`npm install && npm run start`

Connect to the nrepl server that shadow boots for you, which should kick you over to the `shadow.user` namespace.

From there get the cljs-repl going by executing `(shadow/nrepl-select :app)`, then visit http://localhost:3449 in your browser.
