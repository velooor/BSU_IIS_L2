Skip to content
This repository
Search
Pull requests
Issues
Marketplace
Explore
 @velooor
 Sign out
 Watch 1
  Star 0  Fork 0 Uladzislau97/player_position_recognition
 Code  Issues 0  Pull requests 0  Projects 0  Wiki  Insights
Branch: master Find file Copy pathplayer_position_recognition/app/controllers/application_controller.rb
983f124  6 hours ago
@Uladzislau97 Uladzislau97 init
1 contributor
RawBlameHistory
146 lines (116 sloc)  2.8 KB
class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  CLASS_COUNT = 3
  ATTRIBUTE_COUNT = 6

  AVARAGE_SCORES = {
    pac: 62,
    sho: 71,
    pas: 75,
    dri: 79,
    def: 56,
    phy: 70
  }.freeze

  CLASSES = {
    'forward' => [
      [1, 1, 0, 1, 0, 1],
      [1, 1, 1, 1, 0, 1],
      [1, 1, 0, 0, 0, 1],
      [1, 1, 1, 0, 0, 1]
    ],
    'midfielder' => [
      [1, 1, 1, 1, 1, 0],
      [0, 1, 1, 0, 1, 0],
      [0, 0, 1, 0, 1, 1],
      [1, 1, 1, 1, 1, 1],
      [1, 0, 1, 0, 1, 1]
    ],
    'defender' => [
      [1, 0, 0, 0, 1, 1],
      [0, 0, 0, 0, 1, 1],
      [1, 0, 1, 1, 1, 1],
      [1, 0, 1, 1, 1, 0]
    ]
  }.freeze

  HELPER = { a_it: nil }

  def index
    train
  end

  def detect
    redirect_to result_url(
      params: {
        name: params['name'],
        position: recognize(vector)
      }
    )
  end

  def result
    @name = params['name']
    @position = params['position']
  end

  private

  def vector
    result = Array.new(ATTRIBUTE_COUNT)
    result[0] = params['pac'].to_i > AVARAGE_SCORES[:pac] ? 1 : 0
    result[1] = params['sho'].to_i > AVARAGE_SCORES[:sho] ? 1 : 0
    result[2] = params['pas'].to_i > AVARAGE_SCORES[:pas] ? 1 : 0
    result[3] = params['dri'].to_i > AVARAGE_SCORES[:dri] ? 1 : 0
    result[4] = params['def'].to_i > AVARAGE_SCORES[:def] ? 1 : 0
    result[5] = params['phy'].to_i > AVARAGE_SCORES[:phy] ? 1 : 0
    result
  end

  def train
    a_it = Array.new(CLASS_COUNT) { Array.new(ATTRIBUTE_COUNT, 0) }
    b_it = Array.new(CLASS_COUNT) { Array.new(ATTRIBUTE_COUNT, 0) }
    b_t = Array.new(ATTRIBUTE_COUNT)

    CLASS_COUNT.times do |i|
      m = CLASSES.values[i].length
      res = 0

      ATTRIBUTE_COUNT.times do |t|
        m.times do |j|
          res += CLASSES.values[i][j][t]
        end

        b_it[i][t] = res.to_f / m
      end
    end

    ATTRIBUTE_COUNT.times do |t|
      res = 0

      CLASS_COUNT.times do |i|
        res += b_it[i][t]
      end

      b_t[t] = res.to_f / CLASS_COUNT
    end

    CLASS_COUNT.times do |i|
      ATTRIBUTE_COUNT.times do |t|
        a_it[i][t] = (b_it[i][t] - b_t[t]).abs
      end
    end

    HELPER[:a_it] = a_it
  end

  def recognize(vector)
    a = HELPER[:a_it]

    m_k = Array.new(CLASS_COUNT)

    CLASS_COUNT.times do |i|
      m_x = Array.new(CLASSES.values[i].length)

      CLASSES.values[i].length.times do |t|
        m_x[t] = _m(vector, CLASSES.values[i][t], i, a)
      end

      m_k[i] = m_x.max
    end

    determine_position(m_k)
  end

  def _m(x, x_, _i, a)
    res_1 = 0
    res_2 = 0

    ATTRIBUTE_COUNT.times do |j|
      res_1 += (-1)**(_t(x[j], x_[j])) * a[_i][j]
      res_2 += a[_i][j]
    end

    [0, res_1.to_f / res_2].max
  end

  def _t(a, b)
    a == b ? 2 : 1
  end

  def determine_position(m_k)
    CLASSES.keys[m_k.each_with_index.max[1]]
  end
end
© 2017 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
API
Training
Shop
Blog
About