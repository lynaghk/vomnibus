(ns vomnibus.geo.us
  (:use [vomnibus.loader :only [load-resource]]))

(def states (load-resource "vomnibus/geo/us/states.clj"))
(def counties (load-resource "vomnibus/geo/us/counties.clj"))
