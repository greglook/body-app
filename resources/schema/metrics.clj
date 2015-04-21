[
  ; Metrics entities should be stored in the `metrics` partition for locality.
  {:db/id #db/id[:db.part/db],
   :db/ident :metrics,
   :db.install/_partition :db.part/db}



  ;; ## Measurement Record

  {:db/id #db/id[:db.part/db]
   :db/ident :metric/source
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "reference to the source of this measurement"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :metric/subject
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "reference to the (person) subject of this measurement"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :metric/time
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "time the measurement was taken"
   :db.install/_attribute :db.part/db}



  ;; ## Measurement Attributes

  {:db/id #db/id [:db.part/db]
   :db/ident :metric/weight
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "person weight in kilograms (kg)"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id [:db.part/db]
   :db/ident :metric/fat-percent
   :db/valueType :db.type/double
   :db/cardinality :db.cardinality/one
   :db/doc "body composition fat percentage (0.0-1.0)"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id [:db.part/db]
   :db/ident :metric/heart-rate
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "measured heart rate in beats per minute (bpm)"
   :db.install/_attribute :db.part/db}

  ; TODO: caliper skinfolds (mm)

]
