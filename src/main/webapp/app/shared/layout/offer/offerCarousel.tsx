import React from "react";
import { Carousel } from "react-responsive-carousel";
import { IOffer } from '../../model/offer.model'

export interface OfferCarouselProps {
    pictures: Blob[]
}
export const OfferCarousel = ({ pictures }: OfferCarouselProps) => {
    return (
        <Carousel autoPlay>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-1.jpg" />
                <p className="legend">Legend 1</p>
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-2.jpg" />
                <p className="legend">Legend 2</p>
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-3.jpg" />
                <p className="legend">Legend 3</p>
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-4.jpg" />
                <p className="legend">Legend 4</p>
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-5.jpg" />
                <p className="legend">Legend 5</p>
            </div>
        </Carousel>
    )
};
