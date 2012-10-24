(ns vomnibus.geo.world
  (:use [vomnibus.loader :only [load-resource]]))

(def countries (load-resource "vomnibus/geo/world_countries.clj"))

