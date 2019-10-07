
import React, { Component } from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

export interface OfferCarouselProps {
    pictures: Blob[]
}
export const OfferCarousel = ({ pictures }: OfferCarouselProps) => {
    return (
        <Carousel autoPlay showArrows={false} showThumbs={false} showStatus={false}>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-1.jpg" />
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-2.jpg" />
            </div>
            <div>
                <img src="http://lorempixel.com/output/cats-q-c-640-480-3.jpg" />
            </div>
        </Carousel>
    )
};
