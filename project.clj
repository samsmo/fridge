(defproject om-tut "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2727"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.om/om "0.8.1"]
                 [com.cemerick/piggieback "0.1.5"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :source-paths ["src" "target/classes"]

  :clean-targets ["out/om_tut" "out/om_tut.js"]

  :cljsbuild {
    :builds [{:id "om-tut"
              :source-paths ["src"]
              :compiler {
                :output-to "out/om_tut.js"
                :output-dir "out"
                :optimizations :none
                :cache-analysis true
                :source-map true}}]})
