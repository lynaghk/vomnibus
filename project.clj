(defproject com.keminglabs/vomnibus "0.3.0"
  :description "An assortment of useful geographic data, color schemes, &c."
  :dependencies [[org.clojure/clojure "1.3.0"]]
  :profiles {:dev {:dependencies [[cheshire "2.2.2"]]}}

  :plugins [[com.keminglabs/cljx "0.1.0"]]

  :source-paths [".generated/clj" ".generated/cljs"]
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path ".generated/clj"
                   :rules cljx.rules/clj-rules}

                  {:source-paths ["src/cljx"]
                   :output-path ".generated/cljs"
                   :extension "cljs"
                   :rules cljx.rules/cljs-rules}]}
  ;;generate cljx before JAR
  :hooks [cljx.hooks])
