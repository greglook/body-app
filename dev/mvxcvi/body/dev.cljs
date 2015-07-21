(ns mvxcvi.body.dev
  (:require
    [mvxcvi.body.main :as main]
    [figwheel.client :as figwheel]))


(fw/start {:on-jsload main/run
           :websocket-url "ws://localhost:3449/figwheel-ws"})
