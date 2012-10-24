(ns vomnibus.loader)

(defn load-resource [path]
  (read-string (slurp (ClassLoader/getSystemResource path))))
