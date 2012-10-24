(ns vomnibus.core)
;;You don't actually want any of these.
;;I ran these scripts manually to convert D3's JSON into CLJ.

(comment

  (use 'cheshire.core)
  (use '[clojure.string :only [join]])
  
  (.mkdirs (java.io.File. "resources/vomnibus/geo/us"))
  (spit "resources/vomnibus/geo/us/states.clj"
        (prn-str (into {} (map (fn [x] [(-> x :properties :name) x])
                               (:features (parse-string (slurp "../software/d3/examples/data/us-states.json") true))))))
  
  (spit "resources/vomnibus/geo/us/counties.clj"
        (prn-str (:features (parse-string (slurp "../software/d3/examples/data/us-counties.json") true))))

  (spit "resources/vomnibus/geo/world_countries.clj"
        (prn-str (parse-string (slurp "../software/d3/examples/data/world-countries.json") true)))




  
  (spit "src/vomnibus/color_brewer.clj"
        (join "\n"

              ["(ns vomnibus.color-brewer)"

               (join "\n\n"
                     (map #(->> % (map pr-str) (join "\n"))
                          (for [[scheme nums] (parse-string (slurp "brewer.json") true)]
                            (map (fn [[num colors]]
                                   (list 'def
                                         (symbol (str (name scheme) "-" (name num)))
                                         colors))
                                 (remove (fn [[k _]] (= :type k)) nums)))))]))


  ;;;;;;;;;;;;;;;;;;;;;;;
  ;;D3
  ;;;;;;;;;;;;;;;;;;;;;;;

  (spit "src/vomnibus/d3.clj"
        (join "\n"
              ["(ns vomnibus.d3)"
               (str "(def flare " (prn-str (parse-string (slurp "../software/d3/examples/data/flare.json") true)) ")")
               (str "(def flare-imports " (prn-str (parse-string (slurp "../software/d3/examples/data/flare-imports.json") true)) ")")
               ]))


  ;;;;;;;;;;;;;;;;;;;;;;;
  ;;Famous R datasets
  ;;;;;;;;;;;;;;;;;;;;;;;

  (defn df->maps
    "Takes a data frame map from R JSON output and converts into a vector of maps with keys from the data frame columns."
    [df-map]
    (let [ks (keys df-map)]
      (apply (partial map #(zipmap ks %&))
             (map #(get df-map %) ks))))

  
  (.mkdirs (java.io.File. "resources/vomnibus/r/"))
  (spit "resources/vomnibus/r/mtcars.clj"
        (-> (slurp "data/mtcars.json")
                        (parse-string true)
                        df->maps vec pr-str))

  (spit "resources/vomnibus/r/diamonds.clj"
        (-> (slurp "data/diamonds.json")
                        (parse-string true)
                        df->maps vec pr-str))
  

  )
