;; The `user` partition holds user account entities, person entities, and other
;; account-level data that does not change very often.
[
  ;; ## User Account Entities

  ;; A user account maps authentication methods to a set of permissions on other
  ;; entities in the system.

  {:db/id #db/id[:db.part/db]
   :db/ident :user/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "A unique user identifier."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :user/email
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "The user's email address."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :user/owner
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "Reference to the user which administers this entity. This is used
           on many different types of entities."
   :db.install/_attribute :db.part/db}


  ;; ## Person Entities

  ;; Person entities represent physical people with whom metrics are associated.

  {:db/id #db/id[:db.part/db]
   :db/ident :person/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "A unique person identifier."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/name
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "The name of the person."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/birth-date
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "The date the person was born."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :person/sex
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "The genetic sex (male or female) of the person."
   :db.install/_attribute :db.part/db}

  [:db/add #db/id[:db.part/user] :db/ident :person.sex/male]
  [:db/add #db/id[:db.part/user] :db/ident :person.sex/female]

  ; Activity level
  ; BMR settings



  ;; ## Metric Source Entities

  ;; A metric source is a tool which produces measurements.

  {:db/id #db/id[:db.part/db]
   :db/ident :source/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "A unique metric source identifier."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/name
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "The name of the metric source."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/description
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "A description for the metric source."
   :db.install/_attribute :db.part/db}
]
