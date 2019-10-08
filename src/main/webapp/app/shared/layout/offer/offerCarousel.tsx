
import React, { Component } from 'react';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

export interface OfferCarouselProps {
    pictures: Blob[]
    autoPlay?: boolean
}

export const OfferCarousel = ({ pictures, autoPlay = false }: OfferCarouselProps) => {
    return (
        <Carousel autoPlay={autoPlay} infiniteLoop={true} stopOnHover={false} showArrows={false} showThumbs={false} showStatus={false}>
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
