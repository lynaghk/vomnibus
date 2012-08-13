install.packages(c("ggplot2", "rjson"))

library(ggplot2)
library(rjson)

dir.create("../../data/")

write(toJSON(as.list(diamonds))
      , file="../../data/diamonds.json")

write(toJSON(as.list(mtcars))
      , file="../../data/mtcars.json")

