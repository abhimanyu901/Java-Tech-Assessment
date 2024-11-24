package com.hrs.assessment;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking createBooking(Booking booking) {
        booking.setStatus("Created");
        return repository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return repository.findAll();
    }
    
    public List<Booking> getBookings(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).getContent();
    }

    public Optional<Booking> cancelBooking(Long id) {
        Optional<Booking> booking = repository.findById(id);
        booking.ifPresent(b -> {
            b.setStatus("Canceled");
            repository.save(b);
        });
        return booking;
    }
}
