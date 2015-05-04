[
  {:db/id #db/id[:db.part/db],
   :db/ident :diet-log,
   :db.install/_partition :db.part/db}


  ;; ## Regimen

  ;; A _regimen_ is the plan for a diet, including formula, schedules, and other
  ;; information.

  {:db/id #db/id[:db.part/db]
   :db/ident :regimen/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "A unique regimen identifier."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :regimen/name
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "The name of the regimen."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :source/description
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/doc "description for the metric source"
   :db.install/_attribute :db.part/db}



  ;; ## Diet

  ;; A _diet_ is a specific instance of a regimen put into effect. Diets track
  ;; daily goals, food intake, and measurements.

  {:db/id #db/id[:db.part/db]
   :db/ident :diet/id
   :db/valueType :db.type/string
   :db/cardinality :db.cardinality/one
   :db/unique :db.unique/identity
   :db/doc "A unique diet identifier."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :diet/subject
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "A reference to the person following this diet."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :diet/regimen
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "A reference to the regimen the diet is based on."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :diet/begin-date
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "The date the diet begins."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :diet/end-date
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "The date the diet ends."
   :db.install/_attribute :db.part/db}

  ; other variables to plug into the regimen...



  ;; ## Nutrition Log

  {:db/id #db/id[:db.part/db]
   :db/ident :intake/subject
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "A reference to the person the intake is recorded for."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :intake/date
   :db/valueType :db.type/instant
   :db/cardinality :db.cardinality/one
   :db/doc "The date the intake is recorded for."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :intake/min-goals
   :db/isComponent true
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "A reference to set of lower-bound goals for the day."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :intake/max-goals
   :db/isComponent true
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/one
   :db/doc "A reference to set of lower-bound goals for the day."
   :db.install/_attribute :db.part/db}

  {:db/id #db/id[:db.part/db]
   :db/ident :intake/meals
   :db/isComponent true
   :db/valueType :db.type/ref
   :db/cardinality :db.cardinality/many
   :db/doc "References to the meals recorded for the day."
   :db.install/_attribute :db.part/db}

  ; intake has-many meals
  ; each meal has a name, optional notes
  ; meal has-many foods/recipes
  ; a meal *is* a recipe!

  ;; ## Recipe

  ;; _Recipes_ are combinations of certain quantities of food.

  ; - name
  ; - description/notes
  ; - list of ingredients

  ; ingredients:
  ; - quantity
  ; - food|recipe


  ]
