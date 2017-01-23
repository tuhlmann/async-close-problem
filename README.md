# async-close-problem

Demonstrates a significant cpu increase when closing a 
core.async channel
that has a `go-loop` or `while true` loop inside.

## Installation and Usage

```
git clone https://github.com/tuhlmann/async-close-problem

cd async-close-problem
lein repl
;; open a top or process view in another terminal

(start-chan)
;; confirm used cpu for the Java process is very low

(stop-chan)
;; watch used cpu go up

```

## License

Copyright © 2017 Torsten Uhlmann

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
