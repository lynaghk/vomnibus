(ns vomnibus.core)
;;You don't actually want any of these.
;;I ran these scripts manually to convert D3's JSON into CLJ.

(comment

  (use 'cheshire.core)
  (use '[clojure.string :only [join]])


  (spit "src/vomnibus/geo/us.clj"
        (join "\n"
              ["(ns vomnibus.geo.us)"

               (str "(def states "
                    (prn-str (into {} (map (fn [x] [(-> x :properties :name) x])
                                           (:features (parse-string (slurp "../software/d3/examples/data/us-states.json") true)))))
                    ")")

               (str "(def counties "
                    ;;counties is just a list
                    (prn-str (:features (parse-string (slurp "../software/d3/examples/data/us-counties.json") true)))
                    ")")]))

  (spit "src/vomnibus/geo/world.clj"
        (join "\n"
              ["(ns vomnibus.geo.world)"

               (str "(def countries "
                    (prn-str (parse-string (slurp "../software/d3/examples/data/world-countries.json") true))
                    ")")]))

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




  )
