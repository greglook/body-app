[
  ; The `user` partition holds user account entities, person entities, and other
  ; account-level data that does not change very often.


  ;; ## User Account Entities

  {:db/id #db/id[:db.part/db]
   :db/ident :user/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "unique user identifier"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :user/email
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "user email address"
   :db.install/_attribute :db.part/db}



  ;; ## Person Entities

  {:db/id #db/id[:db.part/db]
   :db/ident :person/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "unique person identifier"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/owner
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "reference to the user which administers this person"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/name
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "person name identifier"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/birth-date
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "date a person was born"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/sex
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "genetic sex (male or female)"
   :db.install/_attribute :db.part/db}

  [:db/add #db/id[:db.part/user] :db/ident :person.sex/male]
  [:db/add #db/id[:db.part/user] :db/ident :person.sex/female]

  ; Activity level
  ; BMR settings



  ;; ## Metric Source Entities

  {:db/id #db/id[:db.part/db]
   :db/ident :source/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "unique metric source identifier"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/user
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "reference to the user a metric source belongs to"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/name
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "name of the metric source"
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/description
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "description for the metric source"
   :db.install/_attribute :db.part/db}
]
