(defproject fridge "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2760"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.om/om "0.8.1"]
                ]


  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["out/fridge" "out/fridge.js"]

  :cljsbuild {
    :builds [{:id "fridge"
              :source-paths ["src"]
              :compiler {
                :output-to "out/fridge.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
