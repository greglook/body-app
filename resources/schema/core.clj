(ns mvxcvi.body.data.core
  "Data schemas for body entities."
  (:require
    [schema.core :as s]))


(def IdSlug
  "This schema element represents a user-selectable (but system-unique)
  identifier. Should be short and not contain spaces."
  #"^[a-zA-Z][a-zA-Z_-]+$")


(defn link-to
  "Denotes that the value should be a link whose value will resolve to
  something matching the given schema."
  [schema]
  ; TODO: implement a custom validator here
  ; Should be a multihash
  s/Any)


; Database (stateful) stores tuples of:
; people: [id, slug, secret]
; people_updates: [id, person_id, time, mhash]
; source_history: [id, time, mhash]
; regimen_history: [id, time, mhash]


(comment TopLevel
  (context "/sources" []
    (GET "/" []
      (list-sources))
    (POST "/" [& params]
      (create-source params))

    (context "/:id" [id]
      (GET "/" []
        (show-source id))
      (PUT "/" [& params]
        (update-source id))
      (DELETE "/" []
        (delete-source id))))


  "
  /food/ud2-depletion-shake/ingredients/egg
  /people/greg-look/meals/2015/05/04/post-workout/food/ud2-depletion-shake/ingredients/egg

  /sources/:source-id
  /regimens/:regimen-id
  /food/:food-id
    /ingredients (-> /food?)
  /people/:subject
    /metrics/:year/:month/:day/:epoch
      /source
    /meals/:year/:month/:day/
      /:meal-id
        /ingredients (or 'ingredient.egg'?)
    /diets/:year/:diet-id
      /regimen
  ")



;; ## Measurements

; /sources/:id
(def MeasurementSource
  "Schema for a tool which produces measurements."
  {:id IdSlug
   :name s/String
   (s/optional-key :description) s/String
   ; Weight?
   ; Adjustment?
   })


; /people/:subject/metrics/:year/:month/:day/:epoch
;   /source -> MeasurementSource
(def Measurement
  "Schema for a _measurement_ at a specific point in time."
  {:source (link-to MeasurementSource)
   :time s/Inst
   (s/optional-key :weight) s/Float ; kg
   (s/optional-key :composition) s/Float ; % fat
   (s/optional-key :heart-rate) s/Float ; beats-per-minute
   ; Caliper skinfolds (mm)
   })



;; ## Nutrition & Foods

(def Nutrient
  "Enumeration of keywords describing valid nutrients."
  (s/enum :calories ; kcal
          :protein ; g
          :carbohydrate ; g
          :fat ; g
          ; alcohol?
          ; sodium?
          ; fiber?
          ))


(def NutritionData
  "Schema for a map of nutrients to numeric amounts."
  {Nutrient s/Num})


(declare Food)


(def FoodList
  "Schema for a list of foods, where each element has a quantity and some
  additional information like notes."
  [{:food (link-to Food)
    :quantity s/Num
    (s/optional-key :note) s/String}])


; /food/:id
;   /ingredients -> FoodList
(def Food
  "Food has nutritional information and serving sizes."
  {:id IdSlug
   :name s/String
   (s/optional-key :description) s/String
   :created-at s/Inst
   :nutrition NutritionData
   :serving-size s/Num
   :serving-unit s/Symbol
   (s/optional-key :ingredients) (link-to FoodList)})



;; ## Regimens & Diets

; /regimens/:id
(def Regimen
  "Schema for a _regimen_, the plan for a diet. This includes formulas,
  schedules, and other info."
  {:id IdSlug
   :name s/String
   (s/optional-key :description) s/String})


; /people/:subject/diets/:year/:id
;   /regimen -> Regimen
(def Diet
  "A _diet_ is a specific instance of a regiment put into effect. Diets track
  daily goals, food intake, and measurements."
  {:name s/String
   :regimen (link-to Regimen)
   :begin-date s/Inst
   :end-date s/Inst})


(def Meal
  "A meal is a combination of foods in some amounts, eaten on a certain day."
  {:name s/String
   :time s/Inst
   :nutrition NutritionData
   :food (link-to FoodList)
   (s/optional-key :notes) s/String})


; /people/:subject/meals/:year/:month/:day
;   /:meal-id -> Meal
(def IntakeLog
  "The log for a single day of food intake."
  {:date s/Inst
   :meals [(ref-to Meal)]
   (s/optional-key :min-goals) NutritionData
   (s/optional-key :max-goals) NutritionData
   (s/optional-key :nutrition) NutritionData})



;; ## People

; /people/:subject
(def Person
  "A person is the top-level data structure for a server."
  {:name s/String
   :birth-date s/Inst
   :sex (s/enum :male :female)
   (s/optional-key :bio) s/String
   ; Activity level
   ; BMR settings
   ;:metrics (s/maybe (link-to {s/Str (link-to {s/Str (link-to {s/Str (link-to {s/Str (link-to Measurement)})})})}))
   })
