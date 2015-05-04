[
  {:db/id #db/id[:db.part/db],
   :db/ident :nutrition-data,
   :db.install/_partition :db.part/db}



  ;; ## Nutrients

  {:db/id #db/id[:db.part/db]
   :db/ident :nutrient/calories
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "kilocalories of energy"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :nutrient/protein
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "grams of protein"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :nutrient/carbohydrate
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "grams of carbohydrate"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :nutrient/fat
   :db/valueType :db.type/float
   :db/cardinality :db.cardinality/one
   :db/doc "grams of fat"
   :db.install/_attribute :db.part/db}

  ; alcohol?
  ; sodium?
  ; fiber?

]
